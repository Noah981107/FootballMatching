/*eslint-disable*/

import React from 'react';
import './App.css';

function App() {
  return (
    <div className="App">
      <div className = "green-nav">
        <div className = "title">BaroCha</div>
        <div className = "buttons">
          <button className = "login_button" onClick={()=>alert('login')}>로그인</button>
          <button className = "signUp_button">회원 가입</button>
        </div>
      </div>

    </div>
  );
}


export default App;