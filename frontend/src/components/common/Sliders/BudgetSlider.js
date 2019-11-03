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
    this.props.setBudgetFromParent(budget);
  }

  render() {
    return (
      <View style={{alignItems: "center", padding: 20}}>
        <Slider
          style={{width: 250}}
          onValueChange={this.handleBudgetPicked}
          maximumValue="1000"
          step="10"
          minimumTrackTintColor='#429ef5'
          maximumTrackTintColor='#d3d3d3'
          thumbTintColor='#429ef5'
        />
        <Text>Budget: ${this.state.budget}</Text>
      </View>
    );
  }
}
