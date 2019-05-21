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
    static defaultProps = {
        index: 0,
        comment: '',
        username: '',
        postAt: null,
    };

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
    static get RANGE() {
        return 5;
    }

    static get HALF_RANGE() {
        return 2;
    }

    static defaultProps = {
        handleClick() {
        },
        current: 0,
        last: 0,
    };

    /**
     * @param props
     *      handleClick: function   fetch the target page
     *      current: number         the number of the current page
     *      last: number            the number of the last page
     */
    constructor(props) {
        super(props);
    }

    handleClick(i) {
        this.props.handleClick(i);
    }

    getCentralPager() {
        const props = this.props;
        let start = 0;
        let end = props.last;
        if (end > Pager.RANGE) {
            if (props.current <= (start + Pager.HALF_RANGE)) {
                end = Pager.RANGE;
            } else if (props.current >= (end - (Pager.HALF_RANGE + 1))) {
                start = end - Pager.RANGE;
            } else {
                start = props.current - Pager.HALF_RANGE;
                end = props.current + Pager.HALF_RANGE + 1;
            }
        }
        let pager = [];
        for (let page = start; page < end; page++) {
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
    static defaultProps = {
        handlePost() {
        },
        cookies: {},
    };

    /**
     * @param props
     *      handlePost: function    the action after post comment
     *      cookies: object
     */
    constructor(props) {
        super(props);
        const {cookies} = props;
        this.state = {csrfToken: cookies.get('XSRF-TOKEN')};
    }

    handleSubmit() {
        const state = this.state;
        const form = document.forms[0];
        const props = this.props;
        console.log(form);
        fetch(`/postComment`, {
            method: 'POST',
            headers: {
                'X-XSRF-TOKEN': state.csrfToken,
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            credentials: 'include',
            body: JSON.stringify({'comment': 'abcdefg'}),
        }).finally(props.handlePost);
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
    static defaultProps = {
        boardCommentList: [],
        totalPages: 0,
        totalElements: 0,
        page: 0,
        size: 10,
        cookies: {},
    };

    /**
     * @param props
     *      boardCommentList: array the comments of the current page
     *      totalPages: number      the amount of all pages
     *      totalElements: number   the amount of all comments
     *      page: number            the number of the current page
     *      size: number            the size of a page
     *      cookies: object         set by ReactCookie.withCookies
     */
    constructor(props) {
        super(props);
        this.state = {page: -1, size: 10, boardCommentList: [], totalPages: 0, totalElements: 0};
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
        const props = this.props;
        if (state.page === (state.totalPages - 1)) {
            return (
                <CommentForm cookies={props.cookies}/>
            )
        }
    }

    getPagerComponent() {
        const state = this.state;
        return (
            <Pager current={state.page} last={state.totalPages}
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