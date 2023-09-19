package ru.coffee.shortlink.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.coffee.shortlink.dto.request.FullLinkDto;
import ru.coffee.shortlink.dto.response.StatsDataDto;
import ru.coffee.shortlink.model.Link;
import ru.coffee.shortlink.service.LinkService;

import java.util.List;

@RestController
@Component
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/generate")
    public ResponseEntity<StatsDataDto> shortLink(@RequestBody FullLinkDto original) {

        StatsDataDto shortLinkDto = new StatsDataDto();
        String link = linkService.generate(original.getOriginal());
        shortLinkDto.setLink(link);

        return ResponseEntity
                .ok()
                .body(shortLinkDto);

    }

    @GetMapping("/l/{link}")
    public RedirectView originalLink(@PathVariable("link") String link) {
        linkService.incrementCount(link);
        String fullLink = linkService.findOriginalLink(link);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(fullLink);

        return redirectView;
    }

    @GetMapping("/stats/{link}")
    public ResponseEntity<Link> stats(@PathVariable("link") String link) {

        Link link1 = linkService.findStats(link);

        return ResponseEntity
                .ok()
                .body(link1);
    }

    @GetMapping("/stats")
    public List<Link> getStats(@RequestParam(name = "page") int page,
                               @RequestParam(name = "count") int count) {
        return linkService.findOnPageAndCount(page, count);
    }
}
