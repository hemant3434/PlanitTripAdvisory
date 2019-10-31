import React, { Component } from 'react';
import { Text, View, Button } from 'react-native';
import DateTime from '../pages/DateTime';
import BudgetSlider from '../components/common/Sliders/BudgetSlider';
import DistanceSlider from '../components/common/Sliders/DistanceSlider';

export class Multi extends Component {
  constructor(props){
    super(props)
    this.state = {
      step: 1,
      date: "Date",
      startTime: "Start Time",
      endTime: "End Time",
      budget: "Budget",
      distance: "Distance"
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
            <DateTime
              continue={this.nextStep}
              previous={this.previousStep}
              setDateFromParent={this.setDate}
              setStartTimeFromParent={this.setStartTime}
              setEndTimeFromParent={this.setEndTime}
            />
        );
      case 2:
        return(
          <View style={{paddingTop: 100, justifyContent: 'center', alignItems: 'center'}}>
            <Text>
              {this.state.date}
            </Text>
            <Text>
              {this.state.endTime}
            </Text>
            <Text>
              {this.state.startTime}
            </Text>
            <BudgetSlider
              setBudget={this.setBudget}
            />
            <Text>Value: {this.state.budget}</Text>
            <DistanceSlider
              setDistance={this.setDistance}
            />
            <Text>Value: {this.state.distance}</Text>
          </View>
        );
    }
  }
}

export default Multi;
