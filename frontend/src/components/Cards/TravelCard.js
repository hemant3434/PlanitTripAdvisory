import React, { Component } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { Card, ListItem, Button, Icon } from 'react-native-elements'

class TravelCard extends Component{
  constructor(props){
    super(props)
  }

  render(props){
    return (
      <Card><ListItem
        title={this.props.title}
        leftIcon={{ name: this.props.icon }}
        subtitle={this.props.subtitle}
        chevron
        /></Card>

    );
  }
}

export default TravelCard;
