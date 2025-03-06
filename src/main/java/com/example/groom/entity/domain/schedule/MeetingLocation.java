package com.example.groom.entity.domain.schedule;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class MeetingLocation {
    private String address;
    private String longitude;
    private String latitude;
}
