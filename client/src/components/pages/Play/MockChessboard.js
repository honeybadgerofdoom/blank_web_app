const pieces = {
    white: {
        king: "\u2654",
        queen: "\u2655",
        rook: "\u2656",
        bishop: "\u2657",
        knight: "\u2658",
        pawn: "\u2659"
    },
    black: {
        king: "\u265A",
        queen: "\u265B",
        rook: "\u265C",
        bishop: "\u265D",
        knight: "\u265E",
        pawn: "\u265F"
    },
    empty: ""
}

const mockChessboardInitial = [
    pieces.white.rook,
    pieces.white.knight,
    pieces.white.bishop,
    pieces.white.queen,
    pieces.white.king,
    pieces.white.bishop,
    pieces.white.knight,
    pieces.white.rook,

    pieces.white.pawn,
    pieces.white.pawn,
    pieces.white.pawn,
    pieces.white.pawn,
    pieces.white.pawn,
    pieces.white.pawn,
    pieces.white.pawn,
    pieces.white.pawn,

    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,

    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,

    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,

    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,
    pieces.empty,

    pieces.black.pawn,
    pieces.black.pawn,
    pieces.black.pawn,
    pieces.black.pawn,
    pieces.black.pawn,
    pieces.black.pawn,
    pieces.black.pawn,
    pieces.black.pawn,

    pieces.black.rook,
    pieces.black.knight,
    pieces.black.bishop,
    pieces.black.queen,
    pieces.black.king,
    pieces.black.bishop,
    pieces.black.knight,
    pieces.black.rook,
];

export const mockChessboard = mockChessboardInitial.reverse();

function generateMappingArray() {
    let stringArray = [];
    const letterArray = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
    const numberArray = ['1', '2', '3', '4', '5', '6', '7', '8'];
    numberArray.reverse().forEach((number) => {
        for(let i = 0; i < 8; i++) {
            stringArray.push(letterArray[i] + number);
        }
    })
    console.log(stringArray.reverse())
    return stringArray.reverse();
}

export const positionMap = generateMappingArray();