package ai.miko.assemblylang.service;

import org.springframework.stereotype.Service;

@Service
public interface AssemblyService {
    String process(String code);
    
} 