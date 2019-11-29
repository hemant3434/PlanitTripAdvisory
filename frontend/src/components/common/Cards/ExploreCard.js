import React, { Component } from 'react';
import { Text } from 'react-native';
import { Card, Button, Icon } from 'react-native-elements'
import axios from 'axios';
import * as constClass from '../../../constants/index';
import TimePicker from '../../Filters/TimePicker';

class ImageCard extends Component{
  constructor(props){
    super(props)
    this.fetchData = this.fetchData.bind(this);
    this.state = {
      startHour: 16,
      startMinute: 50,
      endHour: 18,
      endMinute: 50
    }
  }

  fetchData(){
    console.log("ADDING THIS TO EVENTS!", { ...this.props.common,
      startTime: { ...this.props.common.startTime ,
                    hour: this.state.startHour,
                    minute: this.state.startMinute },
      endTime: { ...this.props.common.endTime ,
        hour: this.state.endHour,
        minute: this.state.endMinute }} );
    axios.post(constClass.ADDEVENT_EP, { ...this.props.common,
    startTime: { ...this.props.common.startTime ,
                  hour: this.state.startHour,
                  minute: this.state.startMinute },
    endTime: { ...this.props.common.endTime ,
      hour: this.state.endHour,
      minute: this.state.endMinute }})
    .then(res => {console.log(res)})
    .then(res => {this.props.refresh(res)})
    .catch(error => console.log(error))
  }

  setStartTime

  render(){
    return (
      <Card
        title={this.props.common.title}
        image={this.props.common.image}>
        <Text style={{marginBottom: 10}}>{this.props.common.text}</Text>
        <Button
        icon={<Icon name='code' color='#ffffff'/>}
        title='ADD EVENT' onPress={this.fetchData}/>
        <TimePicker 
        setStartTimeFromParent={(time) => {this.setState({
          startHour: time.slice(0,2),
          startMinute: time.slice(3,5)}
        )}}
        setEndTimeFromParent={(time) => {this.setState({
          endHour: time.slice(0,2),
          endMinute: time.slice(3,5)}
        )}}/>
      </Card>
    );
  }
}

export default ImageCard;
