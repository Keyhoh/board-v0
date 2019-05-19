'use strict';

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {page: -1, size: 10};
    }

    componentDidMount() {
        fetch('http://localhost:8080/getPage?page=0&size=10')
            .then(response => response.json())
            .then(json => this.setState(json));
    }

    render() {
        const state = this.state;
        return (
            <div>
                {JSON.stringify(this.state)}
            </div>
        );
    }
}

const appContainer = document.querySelector('#app');
ReactDOM.render((<App/>), appContainer);