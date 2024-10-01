package baseball;

import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;


public class Application {
    GameRogic game = new GameRogic();
    public static void main(String[] args) {
        game.inGame();
        game.restart();
    }
}

class ComputerNum{
    public List<Integer> GetNum() {
        ArrayList<Integer> com = new ArrayList<>();
        while (com.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!com.contains(randomNumber)) {
                com.add(randomNumber);
            }
        }
        return com;
    }
}

class InputNum{
    public List<Integer> Input() {
        ArrayList<Integer> userNum = new ArrayList<>();
        System.out.print("숫자를 입력해주세요: ");
        String input = Console.readLine();
        String[] splitInput = input.split("");
        for(int i = 0; i<splitInput.length; i++){
            int splitInt = Integer.parseInt(splitInput[i]);
            userNum.add(splitInt);
        }
        return userNum;
    }
}

class Rule{
    public int ball(List<Integer> com, List<Integer> user){
        int ballCount = 0;

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(com.get(i) == user.get(j) && com.get(i) != user.get(i)) ballCount++;
            }
        }
        return ballCount;
    }

    public int strike(List<Integer> com, List<Integer> user){
        int strikeCount = 0;

        for(int i=0; i<3; i++) if(com.get(i) == user.get(i)) strikeCount++;

        return strikeCount;
    }
}

class GameRogic{
    ComputerNum computer = new ComputerNum();
    InputNum userInput = new InputNum();
    Rule rule = new Rule();
    public void inGame(){
        List<Integer> com = computer.GetNum();

        System.out.println("숫자 야구 게임을 시작합니다.");
        while(true){
            userInput.Input();
            System.out.println(rule.ball(com, userInput.Input()) + "볼" + rule.strike(com, userInput.Input()) + "스트라이크");
            if(rule.strike(com, userInput.Input()) == 3) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                break;
            }
        }
    }

    public void restart(){
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String input = Console.readLine();
        if(input == "1") inGame();
        else System.exit(0);
    }
}