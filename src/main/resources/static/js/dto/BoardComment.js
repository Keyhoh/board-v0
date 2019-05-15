export default class BoardComment {
    size;
    boardComment;

    constructor({size = 10, boardComment = ""}) {
        this.size = size;
        this.boardComment = boardComment;
    }
}