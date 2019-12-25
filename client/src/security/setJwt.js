import axios from "axios";
import jwt_decode from 'jwt-decode';

// Sets/Removes/Verifies JWT in Local Storage and HTTP Headers
const setJWTToken = token => {
  if (!token) {
    localStorage.removeItem("jwt");
    delete axios.defaults.headers.common["Authorization"];
    return false;
  }

  if (token) {
    const claims = jwt_decode(token);

    if (Date.now() > claims.exp * 1000) {
      window.alert('Session expired - please login again.');
      localStorage.removeItem("jwt");
      delete axios.defaults.headers.common["Authorization"];
      return false;
    } else {
      axios.defaults.headers.common["Authorization"] = token;
      return true;
    }
  }
};

export default setJWTToken;
