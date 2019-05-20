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
        this.halfRange = 2;
    }

    handleClick(i) {
        this.props.handleClick(i);
    }

    getCentralPager() {
        const props = this.props;
        const start = Math.max(props.current - this.halfRange, 0);
        const stop = Math.min(props.current + this.halfRange, props.last);
        let pager = [];
        for (let page = start; page <= stop; page++) {
            pager.push(<Pagination.Item onClick={() => this.handleClick(page)}
                                        active={page === props.current}>{page}</Pagination.Item>);
        }
        return pager;
    }

    render() {
        const props = this.props;
        return (
            <Pagination>
                <Pagination.First onClick={() => this.handleClick(0)}/>
                <Pagination.Prev onClick={() => this.handleClick(Math.max(props.current - 1, 0))}/>
                {this.getCentralPager()}
                <Pagination.Next onClick={() => this.handleClick(Math.min(props.current + 1, props.last))}/>
                <Pagination.Last onClick={() => this.handleClick(-1)}/>
            </Pagination>
        )
    }
}

const Form = ReactBootstrap.Form;
const Button = ReactBootstrap.Button;

class CommentForm extends React.Component {
    constructor(props) {
        super(props);
    }

    handleSubmit() {
        const form = document.forms[0];
        fetch(`/postComment?`).then(response => response.json())
            .then(json => console.log(json));
    }

    render() {
        return (<Form>
            <Form.Group>
                <Form.Control as='textarea' name='_comment' controlId='commentForm'/>
            </Form.Group>
            <Button variant='primary' onClick={this.handleSubmit.bind(this)}>
                send
            </Button>
        </Form>)
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
        const {cookies} = props;
        console.log(cookies.get('XSRF-TOKEN'));
    }

    fetchComment(page, size) {
        fetch(`/getPage?page=${page}&size=${size}`)
            .then(response => response.json())
            .then(json => json.page < json.totalPages && this.setState(json));
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

    getFormComponent() {
        const state = this.state;
        if (state.page === (state.totalPages - 1)) {
            return (
                <CommentForm/>
            )
        }
    }

    getPagerComponent() {
        const state = this.state;
        return (
            <Pager current={state.page} last={state.totalPages - 1}
                   handleClick={i => this.fetchComment(i, state.size)}/>
        )
    }

    componentDidMount() {
        const state = this.state;
        this.fetchComment(state.page, state.size);
    }

    render() {
        return (
            <Container>
                {this.getCommentComponentList()}
                {this.getFormComponent()}
                {this.getPagerComponent()}
            </Container>
        );
    }
}

const withCookies = ReactCookie.withCookies;
App = withCookies(App);

const CookiesProvider = ReactCookie.CookiesProvider;
const appContainer = document.querySelector('#app');
ReactDOM.render((<CookiesProvider><App/></CookiesProvider>), appContainer);