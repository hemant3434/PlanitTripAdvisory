import React from 'react';
import { StyleSheet, Text, View, ScrollView } from 'react-native';
import Calendar from './../components/common/Calendar/Calendar';
import TimePicker from './../components/Filters/TimePicker';
import BudgetSlider from '../components/common/Sliders/BudgetSlider';
import DistanceSlider from '../components/common/Sliders/DistanceSlider';
import SubmitButton from './../components/common/Buttons/SubmitButton';
import BackButton from './../components/common/Buttons/BackButton';

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

  render(){
    return(
      <ScrollView style={styles.container}>
        <Calendar setDateFromParent={this.setDate}/>
        <TimePicker
          setStartTimeFromParent={this.setStartTime}
          setEndTimeFromParent={this.setEndTime}
        />
        <BudgetSlider setBudgetFromParent={this.setBudget}/>
        <DistanceSlider setDistanceFromParent={this.setDistance}/>
        <SubmitButton continue={this.props.continue}/>
      </ScrollView>
    );
  }
}

let styles = StyleSheet.create({
  container: {
    justifyContent: 'center',
    alignItems: 'center',
  },
});
