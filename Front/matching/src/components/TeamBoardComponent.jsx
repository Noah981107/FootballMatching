import React, { Component } from 'react';
import BoardService from '../service/BoardService';

class TeamBoardComponent extends Component {

    constructor(props){
        super(props)
        this.state = {
            boards : []
        }
    }

    componentDidMount(){
        BoardService.getBoards().then((res) => {
            this.setState({boards : res.data})
            console.log(res);
        });
    }

    render() {
        return (
            <div>
                <h2 className = "tesxt-center">Board List</h2>
                <div className = "row">
                    <table className = "table table-striped table-bordered">
                        <thread>
                            <tr>
                                <th>글번호</th>
                                <th>작성자 </th>
                                <th>팀이름</th>
                                <th>작성일 </th>
                                <th>내용</th>
                            </tr>
                        </thread>
                        <tbody>
                            {
                                this.state.boards.map(
                                    board => 
                                    <tr key = {board.id}>
                                        <td>{board.id}</td>
                                        <td> {board.writer} </td>
                                        <td> {board.teamName} </td>
                                        <td>{board.postDate}</td>
                                        <td> {board.content} </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default TeamBoardComponent;