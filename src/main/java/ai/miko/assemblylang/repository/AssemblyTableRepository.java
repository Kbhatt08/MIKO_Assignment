package ai.miko.assemblylang.repository;

import org.springframework.data.repository.CrudRepository;

import ai.miko.assemblylang.entity.AssemblyTable;

public interface AssemblyTableRepository extends CrudRepository<AssemblyTable, Integer> {

  
}
