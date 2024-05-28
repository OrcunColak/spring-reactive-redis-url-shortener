package com.colak.spring_reactive_redis_url_shortener.jpa;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("links")

@Data
@Accessors(chain = true)
public class LinkEntity {

    @Id
    @Column("link_id")
    private Long id;

    @Column("link_key")
    private String linkKey;

    @Column("link")
    private String link;

    @Column("click_count")
    private Long clickCount;
}
