package com.taru.readshare.dto.link;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.URL;
import java.util.List;

@Getter
@Setter
@ToString
public class LinkDTO {
    private URL url;
    private String title;
    private String description;
    private List<String> tags;
}
