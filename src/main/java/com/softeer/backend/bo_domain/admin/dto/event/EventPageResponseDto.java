package com.softeer.backend.bo_domain.admin.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softeer.backend.bo_domain.admin.dto.main.AdminMainPageResponseDto;
import com.softeer.backend.fo_domain.draw.domain.DrawSetting;
import com.softeer.backend.fo_domain.fcfs.domain.FcfsSetting;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
public class EventPageResponseDto {

    private List<FcfsEvent> fcfsEventList;

    private DrawEvent drawEvent;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class FcfsEvent {

        private int round;

        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
        private LocalDateTime startTime;

        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
        private LocalDateTime endTime;

    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class DrawEvent {

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate startDate;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate endDate;

        @JsonFormat(pattern = "HH:mm:ss")
        private LocalTime startTime;

        @JsonFormat(pattern = "HH:mm:ss")
        private LocalTime endTime;

    }

    public static EventPageResponseDto of(List<FcfsSetting> fcfsSettingList, List<DrawSetting> drawSettingList) {
        List<FcfsEvent> fcfsEventList = fcfsSettingList.stream()
                .map((fcfsSetting) ->
                        EventPageResponseDto.FcfsEvent.builder()
                                .round(fcfsSetting.getRound())
                                .startTime(fcfsSetting.getStartTime())
                                .endTime(fcfsSetting.getEndTime())
                                .build())
                .toList();

        DrawSetting drawSetting = drawSettingList.get(0);

        DrawEvent drawEvent = DrawEvent.builder()
                .startDate(drawSetting.getStartDate())
                .endDate(drawSetting.getEndDate())
                .startTime(drawSetting.getStartTime())
                .endTime(drawSetting.getEndTime())
                .build();

        return EventPageResponseDto.builder()
                .fcfsEventList(fcfsEventList)
                .drawEvent(drawEvent)
                .build();

    }
}