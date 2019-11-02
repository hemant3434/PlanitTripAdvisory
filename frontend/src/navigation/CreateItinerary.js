import React, { Component } from 'react';
import { Text, View, Button } from 'react-native';
import DateTime from '../pages/DateTime';
import ItineraryFilters from '../pages/ItineraryFilters';
import axios from 'axios';

export class Multi extends Component {

  constructor(props){
    super(props)
    this.state = {
      step: 1,
      date: "Date",
      startTime: "Start Time",
      endTime: "End Time",
      budget: "Budget",
      distance: "Distance",
      latitude: null,
      longitude: null,
      location: "Toronto",
      isLoading: true
    };
  }

  setDate = (dataFromChild) => {
    this.setState({
      date: dataFromChild
    })
  }

  setStartTime = (dataFromChild) => {
    this.setState({
      startTime: dataFromChild
    })
  }

  setEndTime = (dataFromChild) => {
    this.setState({
      endTime: dataFromChild
    })
  }

  setBudget = (dataFromChild) => {
    this.setState({
      budget: dataFromChild
    })
  }

  setDistance = (dataFromChild) => {
    this.setState({
      distance: dataFromChild
    })
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
          <ItineraryFilters
            continue={this.nextStep}
            previous={this.previousStep}
            setDateFromParent={this.setDate}
            setStartTimeFromParent={this.setStartTime}
            setEndTimeFromParent={this.setEndTime}
            setBudgetFromParent={this.setBudget}
            setDistanceFromParent={this.setDistance}
          />
        );
    }
  }
}

export default Multi;
