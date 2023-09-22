import { useState } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import { FaBars } from "react-icons/fa";

const StyledHeader = styled.header`
  width: 100%;
  background-color: black;
  display: flex;
  align-items: center;
  padding: 2px 0px 2px 0px;
  justify-content: space-between;
  .menuToggleBtn {
    display: none;
    color: white;
    font-size: 24px;
    position: absolute;
    right: 20px;
    top: 15px;
    cursor: pointer;
  }
  .nav_logo {
      padding: 0 12px;
      .nav-logo-link {
        text-decoration: none;
        font-size: 24px;
        color: black;
        font-weight: bold;
      }
    }

  @media screen and (max-width: 768px) {
    flex-direction: column;
    align-items: flex-start;
    .menuToggleBtn {
      display: block;
    }
  }
`;
const NavMenu = styled.ul`
  list-style: none;
  display: flex;

  li {
    &:hover {
      cursor: pointer;
      background: grey;
      border-radius: 4px;
    }
  }
  .nav-menu-list {
    text-decoration: none;
    color: white;
    display: block;
    padding: 10px 10px;
    font-weight: bold;
  }
  @media screen and (max-width: 768px) {
    display: ${(props) => (props.isToggleOpen ? "block" : "none")};
    flex-direction: column;
    align-items: center;
    width: 100%;
    margin-top: 5px;
  }
`;

const Header = () => {
  const [isToggleOpen, setIsToggleOpen] = useState(false);

  const handleToggleOpen = () => {
    setIsToggleOpen(!isToggleOpen);
  };
  return (
    <>
      <StyledHeader>
        <div className="container">
        <nav className="navbar navbar-expand-lg navbar-default">
        <div className="nav_logo">
          <Link to={"/"} className="nav-logo-link">
            <img src="/img/cspop_logo.png" width="110" height="40" alt="" />
          </Link>
        </div>

        <NavMenu isToggleOpen={isToggleOpen}>
          <li>
            <Link to={"/api/notice"} className="nav-menu-list">
              공지사항
            </Link>
          </li>
          <li>
            <Link to={"/api/graduation/guide"} className="nav-menu-list">
              안내및내규
            </Link>
          </li>
          <li>
            <Link to={"/api/graduation/schedule"} className="nav-menu-list">
              진행일정
            </Link>
          </li>
          <li>
            <Link to={""} className="nav-menu-list">
              졸업자 조회
            </Link>
          </li>
        </NavMenu>
        <FaBars className="menuToggleBtn" onClick={handleToggleOpen} />
        </nav>
        </div>
      </StyledHeader>
    </>
  );
};

export default Header;
