import axios from "axios";
import React, {Component} from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

class LoginPage extends Component{

    constructor(props){
        super(props)
        this.state = {
            id : "",
            pw : ""
        }
    }

    handleID = e =>{
        this.setState(
            {
                id : e.target.value
            }
        );
    }

    handlePW = e =>{
        this.setState(
            {
                pw : e.target.value
            }
        )
    }

    handleSubmit = e =>{
        alert('hi');
        e.preventDefault();
        const login_info = {
            method : "POST",
            mode : "no-cors",
            body : JSON.stringify(this.state), 
            headers : {
                "Content-Type" : "application/json;charset=UTF-8"
            }
        };
        fetch("http://localhost:8080/user/signin", login_info)
            .then(res => {
                return res.json;
            })
            .then(json => {
                console.log(JSON.parse(json));
                if(json.id === this.state.id){
                    alert("로그인 되었습니다.");
                }
            })
    }

    render(){
        return(
            <Router>
                <div>
                    <form onSubmit = {this.handleSubmit}>
                        <div>
                            <span>아이디</span>
                            <input
                                placeholder = "아이디를 입력하세요"
                                value = {this.state.id}
                                onChange = {this.handleID}
                            />
                        </div>
                        <div>
                            <span>비밀번호</span>
                            <input
                                placeholder = "비밀번호를 입력하세요"
                                value = {this.state.pw}
                                onChange = {this.handlePW}
                                type = "password"
                            />
                        </div>
                        <button type = "submit">로그인</button>
                    </form>
                </div>
            </Router>
        );
    }

}

export default LoginPage;