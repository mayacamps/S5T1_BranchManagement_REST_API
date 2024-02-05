package cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.controller;

import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.dto.BranchDto;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.dto.request.BranchRequestDto;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("branch_dto", new BranchRequestDto());
        return "add_form";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("branch_dto") BranchRequestDto branchReqDto, BindingResult bindingResult, RedirectAttributes redirect, Model model){
        BranchDto existingDto = service.getDtoByName(branchReqDto.getName());
        if (existingDto != null){
            bindingResult.reject("duplicate_entry", "Cannot use this name. '" + branchReqDto.getName() + "' already exists.");
        }
        if (bindingResult.hasErrors()) {
            return "add_form";
        }
        service.addBranch(branchReqDto);
        redirect.addFlashAttribute("added_success", "Branch added to the list.");
        return "redirect:/api/v1/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model){
        BranchRequestDto branchRequestDto = service.getReq(id);
        model.addAttribute("branchDto", branchRequestDto);
        model.addAttribute("id", id);
        return "update_form";
    }
    
    @PostMapping("/update/{id}")
    public String updateByName(@PathVariable("id") Integer id, @Valid @ModelAttribute("branchDto") BranchRequestDto branchReqDto, BindingResult bindingResult, RedirectAttributes redirect, Model model){
        BranchDto existingDto = service.getDtoByName(branchReqDto.getName());
        if (!service.existsBranchName(id, branchReqDto.getName())){
            if (existingDto != null){
                bindingResult.reject("duplicate_entry", "Cannot use this name. '" + branchReqDto.getName() + "' already exists.");
            }
        }
        if (bindingResult.hasErrors()) {
            return "update_form";
        }
        if (service.updateBranch(id, branchReqDto)){
            redirect.addFlashAttribute("updated_success", "Branch updated.");
        } else {
            redirect.addFlashAttribute("no_updated", "No changes were made.");
        }
        return "redirect:/api/v1/";
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Integer id){
        service.deleteBranch(id);
        return "redirect:/api/v1/";
    }
}
