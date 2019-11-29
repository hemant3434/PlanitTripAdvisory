import React from 'react';
import Background from '../components/common/LoginRegister/Background';
import Logo from '../components/common/LoginRegister/Logo';
import Header from '../components/common/LoginRegister/Header';
import Button from '../components/common/LoginRegister/Button';
import Paragraph from '../components/common/LoginRegister/Paragraph';
import LoginScreen from '../pages/LoginScreen';
import RegisterScreen from '../pages/RegisterScreen';
import App from '../App.js';
import axios from 'axios';
import * as constClass from '../constants/index';

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
            valid: false,
        }
    }

    // onLoginPressed = (email, password) => {
    //     valid = false;
    //     console.log("login:", email, password);

    //     if (email == "a@a.a") {
    //         console.log("logging in debug mode");
    //         this.setState({step: 4});
    //         valid = true;
    //     }

    //     valid = axios.post(constClass.CHECKPASSWORD_EP, {"email": email, "password": password})
    //     .then(res => {
    //         if (res.data) {
    //             console.log("pw check returns: " + res.data);
    //             if (res.data == "valid") {
    //                 axios.put(constClass.LOGIN_EP, {"email": email})
    //                 .then(resp => console.log(resp));
    //                 this.setState({step: 4})
    //                 return true;
    //             }
    //         }
    //     });
        
    //     console.log("login valid: ", valid);
    //     return valid;
    // };

    // onRegisterPressed = (name, email, password) => {
    //     // valid = false;
    //     console.log("register:", name, email, password);
    //     axios.post(constClass.REGISTER_EP, {"username": name, "email": email, "password": password})
    //     .then(res => {
    //         // if (res.data) {
    //         //     console.log("register returns: " + res.data);
    //         //     this.setState({step: 4})
    //         //     return 1;
    //         // }
    //         this.setState({step: 4});
    //         console.log("register done");
    //        this.state.valid = true;
    //     });
    //     console.log("register valid: ", this.state.valid);
    //     return this.state.valid;
    // };

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
    
    done = () => {
        console.log("done: to app");
        this.setState({step: 4});
    }

    update = () => {
        this.forceUpdate();
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
              <LoginScreen back={this.toHomePage} register={this.toRegisterPage} done={this.done}/>
          );
        case 3:
          return (
              <RegisterScreen back={this.toHomePage} login={this.toLoginPage} done={this.done}/>
          );
        case 4:
          return (
              <App update={this.update}/>
          );
      }
    }
}

export default HomeScreen;
