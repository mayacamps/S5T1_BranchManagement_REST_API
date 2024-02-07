package cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.controller;

import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.dto.BranchDto;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.dto.request.BranchRequestDto;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService service;
    @Tag(name="get",description = "Retrieve saved data")
    @Operation(summary = "Get all Branches",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
                    @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)})
    @GetMapping("/branches")
    public ResponseEntity<List<BranchDto>> getAllBranches(){
         return ResponseEntity.ok().body(service.getAllBranches());
    }

    @Tag(name="get")
    @Operation(summary = "Get Branch by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid ID format supplied", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Branch was not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)})
    @GetMapping("/getOne/{id}")
    public ResponseEntity<BranchDto> getById(@PathVariable("id") Integer id){
         return ResponseEntity.ok().body(service.getDtoById(id));
    }

    @Operation(summary = "Add new Branch",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully added"),
                    @ApiResponse(responseCode = "404", description = "Branch was not found / Country does not exist", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Branch with given name already exists", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)})
    @PostMapping("/add")
    public ResponseEntity<BranchDto> add(@Valid @RequestBody BranchRequestDto branchReqDto){
        return ResponseEntity.ok().body(service.addBranch(branchReqDto));
    }

    @Operation(summary = "Update Branch by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated"),
                    @ApiResponse(responseCode = "400", description = "Invalid ID format supplied", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Branch was not found / Country does not exist", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Branch with given name already exists", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)})
    @PutMapping("/update/{id}")
    public ResponseEntity<BranchDto> updateById(@PathVariable(value = "id") Integer id, @Valid @RequestBody BranchRequestDto branchReqDto){
        return ResponseEntity.ok().body(service.updateBranch(id, branchReqDto));
    }

    @Operation(summary = "Delete Branch by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted"),
                    @ApiResponse(responseCode = "400", description = "Invalid ID format supplied", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Branch was not found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        service.deleteBranch(id);
        return ResponseEntity.ok().body("Branch with ID: " + id + " successfully deleted!");
    }
}
