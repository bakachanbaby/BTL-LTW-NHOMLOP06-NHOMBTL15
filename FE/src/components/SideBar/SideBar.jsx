import { Menu } from "antd";
import "antd/dist/antd.css";
import { Link } from "react-router-dom";
import { PATH } from "../../constants/path";

const SideBar = () => {
  return (
    <div>
      <Menu
        mode="inline"
        defaultSelectedKeys={["1"]}
        style={{ height: "95vh", borderRight: 0, width: "10vw" }}
      >
        <Menu.Item key="1">
          <Link to={PATH.COMPANY}>CÔNG TY</Link>
        </Menu.Item>
       
        <Menu.Item key="4">
          <Link to={PATH.SERVICE}>DỊCH VỤ</Link>
        </Menu.Item>
        <Menu.Item key="5">
          <Link to={PATH.STAFF_BUILDING}>NV TÒA NHÀ</Link>
        </Menu.Item>
        <Menu.Item key="2">
          <Link to={PATH.BILL}>THỐNG KÊ HÓA ĐƠN</Link>
        </Menu.Item>
        <Menu.Item key="3">
          <Link to={PATH.SALARY}>THỐNG KÊ LƯƠNG</Link>
        </Menu.Item>

      </Menu>
    </div>
  );
};

export default SideBar;
