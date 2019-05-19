'use strict';

const Row = ReactBootstrap.Row;
const Col = ReactBootstrap.Col;

const generateKey = () => {
    const src = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    const len = src.length;
    let key = '';
    for (let i = 0; i < 8; i++) {
        key += src.charAt(Math.random() * len);
    }
    return key;
};

class Comment extends React.Component {
    /**
     * @param props
     *      index: number
     *      comment: string
     *      username: string
     *      postAt: date
     */
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Row>
                <Col>
                    <p><b>{this.props.index} {this.props.username} {this.props.postAt}</b></p>
                    <p>{this.props.comment}</p>
                </Col>
            </Row>
        );
    }
}

const Pagination = ReactBootstrap.Pagination;

class Pager extends React.Component {
    constructor(props) {
        super(props);
        this.state = {range: 5, current: 0, last: 0};
    }

    handleClick(i) {
        this.props.handleClick(i);
    }

    getRange() {

    }

    render() {
        const props = this.props;
        return (
            <Pagination>
                <Pagination.First onClick={() => this.handleClick(0)}/>
                <Pagination.Prev onClick={() => this.handleClick(props.current - 1)}/>
                <Pagination.Next onClick={() => this.handleClick(props.current + 1)}/>
                <Pagination.Last onClick={() => this.handleClick(-1)}/>
            </Pagination>
        )
    }
}

const Container = ReactBootstrap.Container;

class App extends React.Component {
    /**
     * @param props
     *      boardCommentList: array the comments of the current page
     *      totalPages: number      the amount of all pages
     *      totalElements: number   the amount of all comments
     *      page: number            the number of the current page
     *      size: number            the size of a page
     */
    constructor(props) {
        super(props);
        this.state = {page: -1, size: 10, boardCommentList: [], totalPages: 0, totalElements: 0};
    }

    fetchComment(page, size) {
        fetch(`/getPage?page=${page}&size=${size}`)
            .then(response => response.json())
            .then(json => {this.setState(json);console.log(json);});
    }

    componentDidMount() {
        const state = this.state;
        this.fetchComment(state.page, state.size);
    }

    getCommentComponentList() {
        const commentList = this.state.boardCommentList;
        return commentList.map(commentInfo =>
            <Comment
                key={generateKey()}
                index={commentInfo.index}
                comment={commentInfo.comment}
                username={commentInfo.username}
                postAt={commentInfo.postAt}
            />);
    }

    getPagerComponent() {
        const state = this.state;
        return (
            <Pager current={state.page} handleClick={i => this.fetchComment(i, state.size)}/>
        )
    }

    render() {
        const commentList = this.getCommentComponentList();
        const pager = this.getPagerComponent();
        return (
            <Container>
                {commentList}
                {pager}
            </Container>
        );
    }
}

const appContainer = document.querySelector('#app');
ReactDOM.render((<App/>), appContainer);