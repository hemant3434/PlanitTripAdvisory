import React, {Component} from 'react';
import {StyleSheet, Text, ScrollView, View} from 'react-native';
import { Slider } from 'react-native-elements';

export default class BudgetSlider extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      budget: 0
    };
  }

  handleBudgetPicked = budget => {
    this.setState({ budget: budget});
    this.props.setBudget(budget);
  }

  render() {
    return (
      <View>
        <Slider
          style={{width: 150}}
          onValueChange={this.handleBudgetPicked}
          maximumValue="100"
          step="5"
          minimumTrackTintColor='#1fb28a'
          maximumTrackTintColor='#d3d3d3'
          thumbTintColor='#1a9274'
        />
      </View>
    );
  }
}
