import React, {Component} from 'react';
import {StyleSheet, Text, ScrollView, View} from 'react-native';
import { Slider } from 'react-native-elements';

export default class DistanceSlider extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      distance: 0
    };
  }

  handleDistancePicked = distance => {
    this.setState({ distance: distance});
    this.props.setDistanceFromParent(distance);
  }

  render() {
    return (
      <View style={{alignItems: "center", padding: 30}}>
        <Slider
          style={{width: 200}}
          onValueChange={this.handleDistancePicked}
          maximumValue="50"
          step="5"
          minimumTrackTintColor='#429ef5'
          maximumTrackTintColor='#d3d3d3'
          thumbTintColor='#429ef5'
        />
        <Text>Distance: {this.state.distance}km</Text>
      </View>
    );
  }
}
