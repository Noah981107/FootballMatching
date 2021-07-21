
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import TeamBoardComponent from './components/TeamBoardComponent';
import Sidebar from './components/Sidebar';
import LoginPage from './components/LoginPage';

function App() {
  return (
    <div className = "App">
      <Router>             
        <HeaderComponent/> 
        <Sidebar></Sidebar>
          <div className="container">
            <Switch>       
              <Route path = "/team-board" component = {TeamBoardComponent}></Route>
              <Route path = "/user/signup" exact component = {LoginPage}></Route>
            </Switch>
          </div>
        <FooterComponent/>
      </Router>
    </div>
  );
}

export default App;