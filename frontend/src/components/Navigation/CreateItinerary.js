import React, { Component } from 'react';
import { Text, View, Button } from 'react-native';

export class Multi extends Component {
  constructor(props){
    super(props)
    this.state = {
      step: 1
    };
  }

  nextStep = () => {
    const { step } = this.state;
    this.setState({
      step: step + 1
    });
  };

  previousStep = () => {
    const { step } = this.state;
    this.setState({
      step: step - 1
    });
  };

  handleChange = input => e => {
    this.setState({ [input]: e.target.value });
  };


  render(){
    const { step } = this.state;
    switch(step){
      case 1:
        return(<View style={{flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: '#d0d0d0'}}>
        <Text>This is Step 1!</Text>
        <Button title="go to next step" onPress={this.nextStep}></Button>
        </View>);
      case 2:
        return(<View style={{flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: '#d0d0d0'}}>
        <Text>This is Step 2!</Text>
        <Button title="go to previous step" onPress={this.previousStep}></Button>
        <Button title="go to next step" onPress={this.nextStep}></Button>
        </View>);
    }
  }
}

export default Multi;
