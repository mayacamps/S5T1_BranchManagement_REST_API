package cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.service.impl;

import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.exceptions.BranchAlreadyExistsException;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.exceptions.BranchNotFoundException;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.exceptions.CountryDoesNotExistException;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.dto.BranchDto;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.dto.request.BranchRequestDto;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.entity.Branch;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.repository.BranchRepository;
import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepo;
    private final List<String> countries = Arrays.asList("Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe");
    @Override
    public List<BranchDto> getAllBranches() {
        List<Branch> branches = branchRepo.findAll();
        return branches.stream().map(this::toDto).toList();
    }

    @Override
    public BranchDto getDtoById(Integer id) {
        Branch branchExisting = branchRepo.findById(id).orElseThrow(() -> new BranchNotFoundException("Branch not found with ID: " + id));
        return toDto(branchExisting);
    }

    @Override
    public BranchDto addBranch(BranchRequestDto branchReqDto) {
        branchRepo.findByName(branchReqDto.getName()).ifPresent(branch -> {
            throw new BranchAlreadyExistsException("Branch already exists with name: " + branch.getName());
        });
        if(countries.stream().noneMatch(branchReqDto.getCountry()::equalsIgnoreCase)) throw new CountryDoesNotExistException("Introduce valid country. Country: '" + branchReqDto.getCountry() + "' does not exist.");
        Branch branch = toEntity(branchReqDto);
        branchRepo.save(branch);
        return toDto(branch);
    }

    @Override
    public BranchDto updateBranch(Integer id, BranchRequestDto branchReqDto) {
        Branch existingBranch = branchRepo.findById(id).orElseThrow(() -> new BranchNotFoundException("Branch not found with ID: " + id));
        if(countries.stream().noneMatch(branchReqDto.getCountry()::equalsIgnoreCase)) throw new CountryDoesNotExistException("Introduce valid country. Country: '" + branchReqDto.getCountry() + "' does not exist.");
        if (!existingBranch.getName().equalsIgnoreCase(branchReqDto.getName())){
            branchRepo.findByName(branchReqDto.getName()).ifPresent(branch -> {
                throw new BranchAlreadyExistsException("Branch already exists with name: " + branch.getName());
            });
            existingBranch.setName(branchReqDto.getName());
        }

        existingBranch.setCountry(branchReqDto.getCountry());
        branchRepo.save(existingBranch);
        return toDto(existingBranch);
    }

    @Override
    public void deleteBranch(Integer id) {
        Branch branchExisting = branchRepo.findById(id).orElseThrow(() -> new BranchNotFoundException("Branch not found with ID: " + id));
        branchRepo.deleteById(branchExisting.getId());
    }

    @Override
    public Branch toEntity(BranchRequestDto reqDto) {
        return new Branch(reqDto.getName(), reqDto.getCountry());
    }

    @Override
    public BranchDto toDto(Branch branch) {
        return new BranchDto(branch.getId(), branch.getName(), branch.getCountry());
    }
}
