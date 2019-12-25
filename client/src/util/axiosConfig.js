// The API_URL may need to be changed depending on where your back-end API is running.
// The default is `http://localhost:8080` (Apache Tomcat default).
// To change it permanently or long-term, include a `.env` file in your build with the values you wish to use.
// To change it temporarily for a particular build, set the variables in your terminal shell before building.

// Reference: https://create-react-app.dev/docs/adding-custom-environment-variables/#adding-temporary-environment-variables-in-your-shell

export const API_URL = process.env.REACT_APP_API_URL || `http://localhost:8080/api`;
