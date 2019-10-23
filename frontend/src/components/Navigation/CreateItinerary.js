import React, { Component } from 'react';
import { Text, View, Button } from 'react-native';
import DateTime from './../../pages/DateTime';
import MapPicker from './../../components/MapPicker';
import Itinerary from './../../pages/Itinerary';

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
        return(
            <DateTime continue={this.nextStep} previous={this.previousStep} />
        );
      case 2:
        return(
            <MapPicker continue={this.nextStep} previous={this.previousStep} />
        );
    }
  }
}

export default Multi;
