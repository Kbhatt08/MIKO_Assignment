package ai.miko.assemblylang.ServiceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.miko.assemblylang.entity.AssemblyTable;
import ai.miko.assemblylang.exception.SyntaxException;
import ai.miko.assemblylang.repository.AssemblyTableRepository;
import ai.miko.assemblylang.service.AssemblyService;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class AssemblyServiceImpl implements AssemblyService{

    Map<String, Integer> datastore;
    String Output;
    @Autowired
    private AssemblyTableRepository repository;

    @Override
    public String process(String code) {
        datastore=new HashMap<>();
        Output="";
        if (code == null) {
            log.error("Code is null: "+ code);
            throwException("At line 1");
        }
        String[] lines=code.split("\n");

        for(int i=0;i<lines.length;i++){
            String[] linearray = lines[i].split(" ");
            if(linearray.length!=2){
                throwException("At line "+ ++i);
            }
            switch (linearray[0]) {
                case "MV":
                    String[]values=linearray[1].split(",");
                    if(values.length!=2){
                        throwException("At line "+ ++i);
                    }
                    Move(values[0].trim(),values[1].trim());
                    break;
                case "SHOW":
                    ShowArgs(linearray[1]);
                    break;
                case "ADD":
                    String[]addvalues=linearray[1].split(",");
                    if(addvalues.length!=2){
                        throwException("At line "+ ++i);
                    }
                    AddArgs(addvalues[0].trim(), addvalues[1].trim());
                    break;
            
                default:
                    throwException("line "+ ++i);
                    break;
            }
        } 
        AssemblyTable assemblyTable=new AssemblyTable();
        assemblyTable.setInputCode(code);
        assemblyTable.setOutput(Output);
        repository.save(assemblyTable);
        return Output;
    }

    private void throwException(String string) {
        throw new SyntaxException("Invalid Syntax : "+ string);
    }

    private void Move(String string, String string2) {
        if(!string2.startsWith("#")){
            datastore.put(string, datastore.get(string2));
        }
        else{
            log.debug("Move: "+ string + " " + string2.substring(1, string2.length()));
            datastore.put(string, Integer.parseInt(string2.substring(1, string2.length())));
        }
    }

    private void AddConstant(String string, String string2) {
        Integer x=datastore.get(string);
        if(x==null){
            x=0;
        }    
        datastore.put(string, x+Integer.parseInt(string2));
    }

    private void AddArgs(String string, String string2) {
        if(isNumeric(string2)){
            AddConstant(string,string2);
        }
        else{
            Integer x=datastore.get(string);
            Integer y=datastore.get(string2);
            datastore.put(string, x+y);
        }
    }

    private void ShowArgs(String string) {
        log.info("Show: "+ string);
        log.info(datastore.get(string)+"\n");
        Output+=datastore.get(string)+"\n";
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
      }
    
}
