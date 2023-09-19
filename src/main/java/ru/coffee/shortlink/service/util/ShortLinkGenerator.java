package ru.coffee.shortlink.service.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class ShortLinkGenerator {

    public String generator(String fullLink) {
        Pattern pattern = Pattern.compile("(http.*)://(\\w*).((\\w*)*)(\\/.*)*");
        Matcher matcher = pattern.matcher(fullLink);
        StringBuilder sb = new StringBuilder();
        while(matcher.find()) {
            String seq = matcher.group();
            sb = sb.append(seq);
        }
        System.out.println(sb);
        return sb.toString().chars()
                .mapToObj(c -> (char) c)
                .map(ch -> ch / 2)
                .limit(5)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
