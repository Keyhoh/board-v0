'use strict';

const e = React.createElement;

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {page: -1, size: 10};
        this.updatePage(0);
    }

    set page(page) {
        const pageNum = Number(page);
        if (!isNaN(pageNum) && pageNum !== this.state.page) this.setState({page: pageNum});
    }

    updatePage(page, size) {
        const pageNum = Number(page);
        const sizeNum = Number(size || this.state.size);
        if (!isNaN(pageNum) && !isNaN(sizeNum)) {
            fetch(`http://localhost:8080/getPage?page=${pageNum}&size=${sizeNum}`)
                .then(response => response.json())
                .then(json => console.log(json));
        }
    }

    render() {
        const xhr = new XMLHttpRequest();
        // TODO: send request to get comments.
        // TODO: render comment list, if success getting the page.
        // TODO: render comment input form, if current page is last.
    }
}

App.service = {};