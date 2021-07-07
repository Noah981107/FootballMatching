import React, { Component } from 'react';

class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {

        }
    }
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div>
                        <div><a href="http://localhost:3000/" className="navbar-brand">바로차</a></div>
                        </div>
                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;