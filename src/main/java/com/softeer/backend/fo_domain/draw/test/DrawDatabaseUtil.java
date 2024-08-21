package com.softeer.backend.fo_domain.draw.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrawDatabaseUtil {
    private final DrawTestFirstWinnerListRepository drawTestFirstWinnerListRepository;
    private final DrawTestSecondWinnerListRepository drawTestSecondWinnerListRepository;
    private final DrawTestThirdWinnerListRepository drawTestThirdWinnerListRepository;
    private final DrawTestParticipantCountRepository drawTestParticipantCountRepository;

    public void increaseDrawParticipationCount() {
        drawTestParticipantCountRepository.increaseParticipantCount();
    }

    public int getRankingIfWinner(Integer userId) {
        if (drawTestFirstWinnerListRepository.existsByUserId(userId)) {
            return 1;
        }

        if (drawTestSecondWinnerListRepository.existsByUserId(userId)) {
            return 2;
        }

        if (drawTestThirdWinnerListRepository.existsByUserId(userId)) {
            return 3;
        }
        return 0;
    }

    public boolean isWinner(Integer userId, int ranking, int winnerNum) {
        if (ranking == 1) {
            return isFirstWinner(userId, winnerNum);
        }
        if (ranking == 2) {
            return isSecondWinner(userId, winnerNum);
        }
        if (ranking == 3) {
            return isThirdWinner(userId, winnerNum);
        }
        return false;
    }

    private boolean isFirstWinner(Integer userId, int winnerNum) {
        if (drawTestFirstWinnerListRepository.count() < winnerNum) {
            DrawTestFirstWinnerList firstWinner = DrawTestFirstWinnerList.builder()
                    .userId(userId)
                    .build();

            drawTestFirstWinnerListRepository.save(firstWinner);
            return true;
        }
        return false;
    }

    private boolean isSecondWinner(Integer userId, int winnerNum) {
        if (drawTestSecondWinnerListRepository.count() < winnerNum) {
            DrawTestSecondWinnerList secondWinner = DrawTestSecondWinnerList.builder()
                    .userId(userId)
                    .build();

            drawTestSecondWinnerListRepository.save(secondWinner);
            return true;
        }
        return false;
    }

    private boolean isThirdWinner(Integer userId, int winnerNum) {
        if (drawTestThirdWinnerListRepository.count() < winnerNum) {
            DrawTestThirdWinnerList thirdWinner = DrawTestThirdWinnerList.builder()
                    .userId(userId)
                    .build();

            drawTestThirdWinnerListRepository.save(thirdWinner);
            return true;
        }
        return false;
    }
}
