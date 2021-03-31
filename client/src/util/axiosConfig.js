// The API_URL should be changed depending on which environment you're running in (local, production, etc)
// See README.md for details
// Also see documentation for additional details:
// - https://create-react-app.dev/docs/adding-custom-environment-variables/#adding-temporary-environment-variables-in-your-shell
// - https://create-react-app.dev/docs/adding-custom-environment-variables/#adding-development-environment-variables-in-env

export const API_URL = process.env.REACT_APP_API_URL || `http://localhost:8080/api`;
