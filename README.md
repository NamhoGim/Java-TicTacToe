# Project #1

## Contents

- 틱택토 게임 구현하기 - [틱택토 레퍼런스](https://ko.wikipedia.org/wiki/%ED%8B%B1%ED%83%9D%ED%86%A0)
  - 틱택토 게임을 UML로 설계하고, Java로 구현하시오.
    - UML로 클래스와 인터페이스 등을 표현하고, 상속/포함 관계를 나타내시오.
    - Java로 구현하면서 설계가 변경되는 경우, UML에 반영하시오.

  - 틱택토 게임의 룰은 기본 룰을 따르시오.

  - 프로그램 실행 후 아래 순서로 동작하도록 구현하시오.

    - 정수 N을 입력받으시오.
    - 각 플레이어가 사람인지 AI인지 입력받으시오.
    - 두 플레이어 중 한 플레이어가 N번 먼저 승리할 경우 게임을 종료하고, 그렇지 않으면 계속 반복하여 플레이하시오.

      - 사람인 경우, 사람의 차례에 좌표로 말을 둘 곳을 입력받으시오.
        - 이미 말이 있는 좌표를 입력받을 경우, 다시 입력 받으시오.
      - AI인 경우, 입력이 허용되는 좌표를 랜덤하게 선택하도록 하시오.
        - (심화) AI 플레이어가 절대로 지지 않도록 인공지능을 구현하시오.

  - 다음 상속 관계를 이용하여 구현하시오.
    - Player 인터페이스를 구현하는 HumanPlayer와 AIPlayer 클래스를 각각 구현하여 사용하시오.
    
## Class Diagram Overview of TicTacToe Project
![TicTacToe class](./overview.png)

## Class Diagram of TicTacToe class 

![TicTacToe class](./TicTacToe.png)

## Class Diagram of Player interface

![Player interface](./Player.png)

## Class Diagram of HumanPlayer class

![HumanPlayer class](./HumanPlayer.png)

## Class Diagram of DummyAIPlayer class

![DummyAIPlayer class](./DummyAIPlayer.png)

## Class Diagram of AIPlayer class

![AIPlayer class](./AIPlayer.png)

