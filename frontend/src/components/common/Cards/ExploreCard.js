import React, { Component } from 'react';
import { Text, View } from 'react-native';
import { Card, Button, Icon } from 'react-native-elements'
import axios from 'axios';
import * as constClass from '../../../constants/index';

class ImageCard extends Component{
  constructor(props){
    super(props)
  }

  fetchData(){
    axios.get(constClass.ADDEVENT_EP, {

    })
    .catch(error => console.log(error));;
  }

  render(){
    return (
      <Card
        title={this.props.common.title}
        image={this.props.common.image}>
        <Text style={{marginBottom: 10}}>{this.props.common.text}</Text>
        <Button
        icon={<Icon name='code' color='#ffffff'/>}
        title='DELETE EVENT' onPress={this.fetchData()}/>
      </Card>
    );
  }
}

export default ImageCard;
