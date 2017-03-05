package com.work.mr.service.mapper;

import com.work.mr.domain.*;
import com.work.mr.service.dto.MwordDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Mword and its DTO MwordDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MwordMapper {

    MwordDTO mwordToMwordDTO(Mword mword);

    List<MwordDTO> mwordsToMwordDTOs(List<Mword> mwords);

    Mword mwordDTOToMword(MwordDTO mwordDTO);

    List<Mword> mwordDTOsToMwords(List<MwordDTO> mwordDTOs);
}
