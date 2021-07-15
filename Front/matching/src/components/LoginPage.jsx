import axios from "axios";
import React, {Component} from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

class LoginPage extends Component{

    constructor(props){
        super(props)
        this.state = {
            id : "",
            password : "",
            name : "",
            phoneNumber : "",
            locationCode : "",
            joinDate : "",
            isDeleted : ""
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
                password : e.target.value
            }
        )
    }

    handleSubmit = e =>{
        alert(this.state.id);
        alert(this.state.password);
        e.preventDefault();
        axios.post('http://localhost:8080/user/signin', {
            id : this.state.id,
            password : this.state.password
          })
          
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
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