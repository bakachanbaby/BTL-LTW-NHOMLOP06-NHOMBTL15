import { Avatar } from "antd";
import "antd/dist/antd.css";
import styled from "styled-components";

const Container = styled.div`
  display: flex;
  justify-content: space-between;
  height: 5vh;
  background-color: #69b7ff;
  color: white;
`;

const Logo = styled.div`
  background-color: #1890ff;
  width: 10vw;
`;

const User = styled.div`
  display: flex;
  align-items: center;
  margin-right: 20px;
`;

const Header = () => {
  return (
    <Container>
      <Logo>
        <h1 style={{ color: "white", padding: "0px 28px" }}>BTL WEB</h1>
      </Logo>
      <User>
        <span style={{ marginRight: "5px" }}>Hello, Mr Dương Trần Đức</span>
        <Avatar src="https://scontent-hkt1-2.xx.fbcdn.net/v/t39.30808-6/261479591_4576925285735533_1519661011470858089_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=a8JjSp5VFdQAX_c7D11&_nc_ht=scontent-hkt1-2.xx&oh=00_AT_Smer7TsUc29FSHgKSkhNYIsspLFzRghsPUYsO7_6ihA&oe=6299B061" />
      </User>
    </Container>
  );
};

export default Header;
