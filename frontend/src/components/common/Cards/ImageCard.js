import React, { Component } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { Card, ListItem, Button, Icon } from 'react-native-elements'

class ImageCard extends Component{
  constructor(props){
    super(props)
  }

  render(){
    return (
      <Card
        title={this.props.title}
        image={this.props.image}>
        <Text style={{marginBottom: 10}}>{this.props.text}</Text>
        <Button
        icon={<Icon name='code' color='#ffffff' />}
        buttonStyle={{borderRadius: 0, marginLeft: 0, marginRight: 0, marginBottom: 0}}
        title='VIEW EVENT' />
      </Card>
    );
  }
}

export default ImageCard;
