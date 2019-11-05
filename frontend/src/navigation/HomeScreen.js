import React from 'react';
import Background from '../components/common/LoginRegister/Background';
import Logo from '../components/common/LoginRegister/Logo';
import Header from '../components/common/LoginRegister/Header';
import Button from '../components/common/LoginRegister/Button';
import Paragraph from '../components/common/LoginRegister/Paragraph';
import LoginScreen from '../pages/LoginScreen';
import RegisterScreen from '../pages/RegisterScreen';
import ExplorePage from '../pages/ExplorePage';
import App from '../App.js';

class HomeScreen extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            /*
            1 - home screen
            2 - login page
            3 - register page
            4 - rest of the app
            */
            step: 1,
        }
    }

    onLoginPressed = () => {
        this.setState({step: 4})
    };

    toHomePage = () => {
        // console.log("to home");
        this.setState({step: 1});
    }

    toLoginPage = () => {
        // console.log("to login");
        this.setState({step: 2});
    }

    toRegisterPage = () => {
        // console.log("to register");
        this.setState({step: 3});
    }

    render() {
      const { step } = this.state;
      switch(step){
        case 1:
          return(
              <Background>
                  <Logo />
                  <Header>Planit</Header>

                  <Paragraph>
                  Welcome to Planit.
                  </Paragraph>
                  <Button mode="contained" onPress={this.toLoginPage}>
                  Login
                  </Button>
                  <Button
                  mode="outlined"
                  onPress={this.toRegisterPage}
                  >
                  Sign Up
                  </Button>
              </Background>
          );
        case 2:
          return(
              <LoginScreen back={this.toHomePage} register={this.toRegisterPage} onLoginPressed={this.onLoginPressed}/>
          );
        case 3:
          return (
              <RegisterScreen back={this.toHomePage} login={this.toLoginPage}/>
          );
        case 4:
          return (
              <App />
          );
      }
    }
}

export default HomeScreen;
