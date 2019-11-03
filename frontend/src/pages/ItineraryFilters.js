import React from 'react';
import { StyleSheet, Text, View, ScrollView } from 'react-native';
import Calendar from './../components/common/Calendar/Calendar';
import TimePicker from './../components/Filters/TimePicker';
import BudgetSlider from '../components/common/Sliders/BudgetSlider';
import DistanceSlider from '../components/common/Sliders/DistanceSlider';
import SubmitButton from './../components/common/Buttons/SubmitButton';
import TransportationPicker from './../components/common/Transportation/TransportationPicker';

export default class ItineraryFilters extends React.Component {
  constructor(props) {
    super(props)
  }

  setDate = (dataFromChild) => {
    this.props.setDateFromParent(dataFromChild)
  }

  setStartTime = (dataFromChild) => {
    this.props.setStartTimeFromParent(dataFromChild)
  }

  setEndTime = (dataFromChild) => {
    this.props.setEndTimeFromParent(dataFromChild)
  }

  setBudget = (dataFromChild) => {
    this.props.setBudgetFromParent(dataFromChild)
  }

  setDistance = (dataFromChild) => {
    this.props.setDistanceFromParent(dataFromChild)
  }

  setTransportation = (dataFromChild) => {
    this.props.setTransportationFromParent(dataFromChild)
  }

  render(){
    return(
      <ScrollView contentContainerStyle={{flexGrow: 1, justifyContent: "center", padding: 10}}>
        <Calendar setDateFromParent={this.setDate}/>
        <TimePicker
          setStartTimeFromParent={this.setStartTime}
          setEndTimeFromParent={this.setEndTime}
        />
        <BudgetSlider setBudgetFromParent={this.setBudget}/>
        <DistanceSlider setDistanceFromParent={this.setDistance}/>
        <TransportationPicker setTransportationFromParent={this.setTransportation}/>
        <SubmitButton continue={this.props.continue}/>
      </ScrollView>
    );
  }
}

let styles = StyleSheet.create({
  container: {
    flex: 2,
    justifyContent: 'center',
    alignItems: 'center',
  },
});
