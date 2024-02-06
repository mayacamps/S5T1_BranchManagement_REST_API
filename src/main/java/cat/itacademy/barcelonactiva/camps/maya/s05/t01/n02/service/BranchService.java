package cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.service;

import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.dto.BranchDto;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.dto.request.BranchRequestDto;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.entity.Branch;

import java.util.List;

public interface BranchService {
    public List<BranchDto> getAllBranches();
    public BranchDto getDtoById (Integer id);
    public BranchDto addBranch(BranchRequestDto branchReqDto);
    public BranchDto updateBranch(Integer id, BranchRequestDto branchReqDto);
    public void deleteBranch(Integer id);
    public Branch toEntity(BranchRequestDto branchReqDto);
    public BranchDto toDto(Branch branch);
}
