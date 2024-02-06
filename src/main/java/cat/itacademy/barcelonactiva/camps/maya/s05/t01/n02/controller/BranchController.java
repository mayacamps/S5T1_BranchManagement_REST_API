package cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.controller;

import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.dto.BranchDto;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.dto.request.BranchRequestDto;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService service;
    @Operation(summary = "Get all Branches")
    @GetMapping("/branches")
    public ResponseEntity<List<BranchDto>> getAllBranches(){
         return ResponseEntity.ok().body(service.getAllBranches());
    }

    @Operation(summary = "Get Branch by ID")
    @GetMapping("/getOne/{id}")
    public ResponseEntity<BranchDto> getById(@PathVariable("id") Integer id){
         return ResponseEntity.ok().body(service.getDtoById(id));
    }

    @Operation(summary = "Add new Branch")
    @PostMapping("/add")
    public ResponseEntity<BranchDto> add(@Valid @RequestBody BranchRequestDto branchReqDto){
        return ResponseEntity.ok().body(service.addBranch(branchReqDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BranchDto> updateByName(@PathVariable(value = "id") Integer id, @Valid @RequestBody BranchRequestDto branchReqDto){
        return ResponseEntity.ok().body(service.updateBranch(id, branchReqDto));
    }

    @Operation(summary = "Delete Branch by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        service.deleteBranch(id);
        return ResponseEntity.ok().body("Branch with ID: " + id + " successfully deleted!");
    }
}
