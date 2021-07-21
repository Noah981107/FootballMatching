  
import React from "react";
import {Link} from "react-router-dom";

import SidebarItem from "./SidebarItem";

function Sidebar(){

    const menus = [
        { name: "로그인", path: "/user/signup" },
        { name: "사업자 로그인", path: "/business-user/signup" },
        { name: "풋살 구장", path: "/field" },
        { name: "팀원 모집 게시판", path: "/team-board" },
        { name: "용병 모집 게시판", path: "/player-board" },
        { name: "예약", path: "/reservation" }
      ]; 

    return (
        <div className="sidebar">
            { menus.map((menu, index) => {
                return (
                    <Link to={menu.path} key={index}>
                        <SidebarItem
                            menu={menu}
                        />
                    </Link>
                );      
            })}
        </div>
  );
}

export default Sidebar;