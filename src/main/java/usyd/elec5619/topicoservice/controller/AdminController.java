package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.dto.community.CreateCommunityDto;
import usyd.elec5619.topicoservice.dto.community.UpdateCommunityDto;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.CommunityService;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    CommunityService communityService;

    @PostMapping("/communities")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommonResponse<Community> createCommunity(@RequestBody @Valid CreateCommunityDto createCommunityDto) {
        Community community = communityService.createCommunity(createCommunityDto);
        return CommonResponse.success(community);
    }


    @PutMapping("/communities/{communityId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommonResponse<Community> updateCommunity(@PathVariable @Valid Long communityId, @RequestBody @Valid UpdateCommunityDto updateCommunityDto) {
        Community community = communityService.updateCommunity(communityId, updateCommunityDto);
        return CommonResponse.success(community);
    }

    @DeleteMapping("/communities/{communityId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommonResponse<Void> deleteCommunity(@PathVariable @Valid Long communityId) {
        communityService.deleteCommunity(communityId);
        return CommonResponse.success();
    }
}
