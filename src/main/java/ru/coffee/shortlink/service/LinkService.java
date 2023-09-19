package ru.coffee.shortlink.service;

import org.springframework.stereotype.Service;
import ru.coffee.shortlink.model.Link;
import ru.coffee.shortlink.repository.LinkRepository;
import ru.coffee.shortlink.service.util.ShortLinkGenerator;

import java.util.List;

@Service
public class LinkService {

    private final ShortLinkGenerator shortLinkGenerator;
    private final LinkRepository linkRepository;

    public LinkService(ShortLinkGenerator shortLinkGenerator, LinkRepository linkRepository) {
        this.shortLinkGenerator = shortLinkGenerator;
        this.linkRepository = linkRepository;
    }


    public String generate(String original) {
        String link = shortLinkGenerator.generator(original);
        linkRepository.storeLink(link, original);
        return link;

    }

    public String findOriginalLink(String link) {
        return linkRepository.findFullLink(link);
    }

    public Link findStats(String link) {
        return linkRepository.findStats(link);
    }

    public void incrementCount(String link) {
        linkRepository.changeCount(link);
    }

    public List<Link> findOnPageAndCount(int page, int count) {
        return linkRepository.findPageAndCount(page, count);

    }
}
