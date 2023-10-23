package usyd.elec5619.topicoservice.mock;

import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import usyd.elec5619.topicoservice.dto.community.CreateCommunityDto;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.service.CommunityService;
import usyd.elec5619.topicoservice.service.ImageService;

@Component
@RequiredArgsConstructor
public class MockCommunity {
    private final CommunityService communityService;

    public void createCommunities() {
        this.createCommunity(CreateCommunityDto.builder().name("Genshin Impact").description("Genshin Impact description").avatar(1L).banner(1L).build());
        this.createCommunity(CreateCommunityDto.builder().name("CSGO").description("CSGO description").avatar(1L).banner(1L).build());
        this.createCommunity(CreateCommunityDto.builder().name("LoL").description("LoL description").avatar(1L).banner(1L).build());
    }

    public void createCommunity(CreateCommunityDto createCommunityDto) {
        try {
            communityService.createCommunity(createCommunityDto);
        } catch (BadRequestException e) {
            return;
        }
    }
}
