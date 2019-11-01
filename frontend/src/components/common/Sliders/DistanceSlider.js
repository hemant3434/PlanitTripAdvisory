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
    this.props.setDistance(distance);
  }

  render() {
    return (
      <View>
        <Slider
          style={{width: 150}}
          onValueChange={this.handleDistancePicked}
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
